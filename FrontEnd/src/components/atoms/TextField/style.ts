import styled from '@emotion/styled';

export const TextFieldWrapper = styled.div`
  position: relative;
  margin: 3rem 0;
  width: 100%;
`;

export const Input = styled.input`
  border: 0;
  border-bottom: 2px solid #333;
  padding: 15px 0;
  display: block;
  font-size: 18px;
  width: 100%;
  transition: 0.1s ease-in;
  &:focus,
  &:valid {
    border-bottom-color: ${({ theme }) => theme.color.olive};
    outline: none;
  }

  &:focus + label,
  &:valid + label {
    color: border-bottom-color: ${({ theme }) => theme.color.olive};
    transform: translateY(-30px);
  }
`;

export const Label = styled.label`
  position: absolute;
  top: 15px;
  left: 0;
  display: inline-block;
  font-size: 18px;
  min-width: 5px;
  transition: 0.3s cubic-bezier(0.68, -0.55, 0.265, 1.55);
`;
export default {
  TextFieldWrapper,
  Input,
  Label,
};
