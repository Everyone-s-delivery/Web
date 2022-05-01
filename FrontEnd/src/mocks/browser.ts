import { setupWorker } from 'msw';

import handlers from './handlers';

const serviceWorker = setupWorker(...handlers());

export default serviceWorker;
