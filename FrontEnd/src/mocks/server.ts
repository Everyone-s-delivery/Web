import { setupServer } from 'msw/node';

import handlers from './handlers';

const serviceServer = setupServer(...handlers());

export default serviceServer;
