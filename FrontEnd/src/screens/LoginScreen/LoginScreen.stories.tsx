import { Meta, Story } from '@storybook/react';

import LoginScreen from './LoginScreen';

export default {
  title: 'Pages/LoginScreen',
  component: LoginScreen,
  parameters: {
    docs: {
      description: {
        component: '로그인 페이지',
      },
    },
  },
} as Meta;

const Template: Story = (args) => <LoginScreen {...args} />;

export const DefaultLogin = Template.bind({});
