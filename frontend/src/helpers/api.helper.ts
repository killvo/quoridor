import * as queryString from 'query-string';
import { toastr } from 'react-redux-toastr';
import { IFetchArgsData } from 'models/IFetchArgsData';
import { IFetchArgs } from 'models/IFetchArgs';

const formatEndpoint = endpoint => {
  let formattedEndpoint = endpoint;
  if (!formattedEndpoint.startsWith('/')) {
    formattedEndpoint = `/${formattedEndpoint}`;
  }
  if (!formattedEndpoint.startsWith('/api/')) {
    formattedEndpoint = `/api${formattedEndpoint}`;
  }
  return formattedEndpoint;
};

const getFetchUrl = ({ endpoint, queryParams }: IFetchArgsData) => `${formatEndpoint(endpoint)}${
  queryParams ? `?${queryString.stringify(queryParams)}` : ''
}`;

const getInitHeaders = (contentType = 'application/json', hasContent = true) => {
  const headers: HeadersInit = new Headers();
  if (hasContent) {
    headers.append('content-type', contentType);
  }
  return headers;
};

const getFetchArgs = (args: IFetchArgsData): IFetchArgs => {
  const headers = getInitHeaders();

  if (args.requestData && args.method === 'GET') {
    throw new Error('GET request does not support request body.');
  }

  let body = {};
  if (args.method !== 'GET') {
    body = { body: JSON.stringify(args.requestData) };
  }

  return {
    method: args.method,
    headers,
    ...body
  };
};

let retryOrThrowFailedResponse;

export const callApi = async (args: IFetchArgsData): Promise<any | string> => {
  const fetchArgs = getFetchArgs(args);
  const res = await fetch(getFetchUrl(args), fetchArgs);
  if (!res.ok) {
    return retryOrThrowFailedResponse(res, args);
  }

  if (res.headers.get('content-type') === 'application/json') {
    return res.json();
  }
  return res.text();
};

retryOrThrowFailedResponse = async (res: Response) => {
  let parsedException = { message: 'Can\'t read the response' };
  try {
    parsedException = await res.json();
  } catch (err) {
    // eslint-disable-next-line no-console
    console.error('An error occurred, can\'t read the response', err);
    toastr.error('Error', err);
  }
  throw parsedException;
};
