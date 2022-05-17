import React, { ButtonHTMLAttributes } from 'react';
import { ColorCode } from '@src/constants/color';
import { TextSizeType } from '@src/constants/size';

import { ButtonStyle } from './style';

export interface ButtonProps {
  type?: 'button' | 'submit' | 'reset';
  innerText: string;
  buttonColor: ColorCode;
  textColor: ColorCode;
  textSize?: TextSizeType;
  hasBorder?: boolean;
  hasShadow?: boolean;
  hasFixedWidth?: boolean;
  onHover?: boolean;
  icon?: React.ReactElement;
  width?: string;
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

const Button: React.FC<ButtonProps> = ({
  type = 'submit',
  innerText,
  buttonColor,
  textColor,
  textSize = 'medium',
  hasBorder = false,
  hasShadow = false,
  hasFixedWidth = false,
  icon,
  width = '100%',
  onClick,
  onHover = true,
}) => {
  return (
    <ButtonStyle
      type={type}
      buttonColor={buttonColor}
      textColor={textColor}
      textSize={textSize}
      hasBorder={hasBorder}
      hasShadow={hasShadow}
      hasFixedWidth={hasFixedWidth}
      onHover={onHover}
      width={width}
      onClick={onClick}
    >
      {icon}
      {innerText}
    </ButtonStyle>
  );
};

export default Button;
