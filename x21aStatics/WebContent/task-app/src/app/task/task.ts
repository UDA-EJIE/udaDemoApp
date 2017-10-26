import { List } from '../list/list';

export class Task {
  id: number;
  idList: number;
  list: List;
  name: string;
  detail: string;
  done: boolean;
}
