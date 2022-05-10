import { ActionType, StateType } from 'typesafe-actions';

declare module 'typesafe-actions' {
  export type RootState = StateType<typeof import('./reducers').default>;
  export type RootAction = ActionType<typeof import('./actions').default>;
  interface Types {
    RootAction: RootAction;
  }
}
