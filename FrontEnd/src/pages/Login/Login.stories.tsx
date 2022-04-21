import { Meta, Story } from '@storybook/react';

import Login from './Login';

export default {
  title: 'Pages/LoginScreen',
  component: Login,
  parameters: {
    docs: {
      description: {
        component: '로그인 페이지',
      },
    },
  },
} as Meta;

const Template: Story = (args) => <Login {...args} />;

export const DefaultLogin = Template.bind({});
