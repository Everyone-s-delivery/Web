import React from 'react';
import { ColorCode } from '@src/constants/color';
import { TextSizeType } from '@src/constants/size';

import { ButtonStyle } from './Button.style';

export interface ButtonProps {
  innerText: string;
  buttonColor: ColorCode;
  textColor: ColorCode;
  textSize?: TextSizeType;
  hasBorder?: boolean;
  hasShadow?: boolean;
  hasFixedWidth?: boolean;
  onHover?: boolean;
  handleClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

const Button: React.FC<ButtonProps> = ({
  innerText,
  buttonColor,
  textColor,
  textSize = 'medium',
  hasBorder = false,
  hasShadow = false,
  hasFixedWidth = false,
  handleClick,
  onHover = true,
}) => {
  return (
    <ButtonStyle
      buttonColor={buttonColor}
      textColor={textColor}
      textSize={textSize}
      hasBorder={hasBorder}
      hasShadow={hasShadow}
      hasFixedWidth={hasFixedWidth}
      onHover={onHover}
      onClick={handleClick}
    >
      {innerText}
    </ButtonStyle>
  );
};

export default Button;
