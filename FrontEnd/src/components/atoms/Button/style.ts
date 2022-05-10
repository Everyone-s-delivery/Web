import styled from '@emotion/styled';
import { getColorValueFromCode } from '@src/constants';

import { getTextSizeFromCode } from '../../../constants/size';
import { ButtonProps } from '.';
type ButtonStyleProps = Omit<ButtonProps, 'innerText' | 'handleClick'>;

const getHeightFromTextSize = (size: ButtonProps['textSize'] = 'medium'): string => {
  switch (size) {
    case 'large':
      return '5.2rem';
    case 'medium':
      return '4.4rem';
    case 'small':
      return '3rem';
  }
};

export const ButtonStyle = styled.button<ButtonStyleProps>(
  ({ buttonColor, textColor, textSize, hasBorder, hasShadow, width, hasFixedWidth, onHover }) => `
    border: 0rem;
    border-radius: 10rem;
    padding: 0rem 4rem;
    font-size: ${getTextSizeFromCode(textSize)};
    height: ${getHeightFromTextSize(textSize)};
    transition: 0.5s;
    font-weight: 400;
    line-height: normal;
    width: ${width};
    &:hover {
        cursor: pointer;
        border-color: ${getColorValueFromCode(buttonColor)};
        color: ${getColorValueFromCode(buttonColor)};
        background-color: ${getColorValueFromCode(textColor)};
    }
    color: ${getColorValueFromCode(textColor)};
    border-color ${getColorValueFromCode(textColor)};
    background-color: ${getColorValueFromCode(buttonColor)};
    font-size: ${textSize};
    cursor: pointer;
    align-items: center;
    span {
      display: inline-block;
      font-size: 1rem;
      align-items: center;
      color: rgba(0, 0, 0, 0.85);
      vertical-align: middle;
      padding-bottom: 0.2rem;
    }
    svg {
      height: 1.5rem;
      margin-right: 0.75rem;
    }
    ${
      hasBorder &&
      `
      border-style: solid;
      border-width: 0.1rem;
      `
    }
    ${
      hasShadow &&
      `
      box-shadow: 0px 3px 15px rgba(0, 0, 0, 0.15);
      `
    }
    ${
      hasFixedWidth &&
      `
      padding: ${
        textSize === 'medium'
          ? `
      padding: 0rem 10.6rem;
      `
          : `
      padding: 0rem 13rem;
      
      `
      }
      `
    }


    
    `
);
