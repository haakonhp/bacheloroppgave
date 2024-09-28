import { TextField } from "@mui/material";
import React from "react";

const TextFieldComponent = ({
  label,
  data,
  type,
  dataVar,
  updateFormData,
  disabled,
}: {
  label: string;
  data: string;
  type: React.HTMLInputTypeAttribute | undefined;
  dataVar: string | number;
  updateFormData: (e: any) => void;
  disabled: boolean;
}) => {
  return (
    <TextField
      sx={{ mb: 2 }}
      disabled={disabled}
      id="standard-basic"
      label={label}
      required
      error={!dataVar}
      defaultValue={dataVar}
      name={data}
      variant="outlined"
      type={type}
      onChange={updateFormData}
    />
  );
};

export default TextFieldComponent;
