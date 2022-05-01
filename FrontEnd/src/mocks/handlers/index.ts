import postHandlers from './src/post';
/**
 * API
 */
export default function handlers() {
  return [...Object.values(postHandlers)];
}
