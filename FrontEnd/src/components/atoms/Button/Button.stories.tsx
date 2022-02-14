import Button, { ButtonProps } from '@src/components/atoms/Button';
import { Meta, Story } from '@storybook/react';

export default {
  title: 'Design System/Atoms/Button',
  component: Button,
  parameters: {
    docs: {
      description: {
        component: 'Button',
      },
    },
  },
  argTypes: {
    innerText: {
      type: 'string',
    },
    buttonColor: {
      options: ['kakao'],
    },
    textColor: {
      options: ['black', 'white'],
    },
    textSize: {
      options: ['small', 'medium', 'large'],
    },
    hasBorder: {
      type: 'boolean',
    },
    hasShadow: {
      type: 'boolean',
    },
    hasFixedWidth: {
      type: 'boolean',
    },
  },
} as Meta;

const Template: Story<ButtonProps> = (args) => <Button {...args} />;
export const kakaoButton = Template.bind({});

kakaoButton.args = {
  innerText: '카카오로 로그인',
  buttonColor: 'kakao',
  textColor: 'black',
  textSize: 'medium',
  hasBorder: false,
  hasShadow: false,
  hasFixedWidth: false,
};
