import React from 'react';
import { FieldHookConfig, useField } from 'formik';

import { Input, Label, TextFieldWrapper } from './style';

const TextField = (props: FieldHookConfig<string> & { label: string }) => {
  const [field] = useField(props);
  const { required, label } = props;
  return (
    <TextFieldWrapper>
      <Input type={props.type} required={required} {...field} />
      <Label htmlFor={field.name}>{label}</Label>
    </TextFieldWrapper>
  );
};

export default TextField;
