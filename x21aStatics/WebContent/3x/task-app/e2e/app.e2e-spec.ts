import { TaskAppPage } from './app.po';

describe('task-app App', () => {
  let page: TaskAppPage;

  beforeEach(() => {
    page = new TaskAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
