"use client";
import { Box, Grid, alpha, styled } from "@mui/material";
import Slider, { SliderThumb } from "@mui/material/Slider";
import clsx from "clsx";
import PropTypes from "prop-types";
import EmojiFlagsIcon from "@mui/icons-material/EmojiFlags";
import PersonPinIcon from "@mui/icons-material/PersonPin";

import React, { useEffect, useMemo, useState } from "react";
import { MATURITY_DEFINITION } from "@/types/MaturityEnums";
import { NumberInput } from "./InputField";

interface Mark {
  value: number;
  label: string;
  color: string;
}

const marks: Mark[] = [
  {
    value: -2,
    label: "Utelukket",
    color: "#D3D3D3",
  },
  {
    value: -1,
    label: "Start",
    color: "#000000",
  },
  {
    value: 0,
    label: MATURITY_DEFINITION.MATURITY0,
    color: "#A9A9A9",
  },
  {
    value: 1,
    label: MATURITY_DEFINITION.MATURITY1,
    color: "#F08989",
  },
  {
    value: 2,
    label: MATURITY_DEFINITION.MATURITY2,
    color: "#F0B389",
  },
  {
    value: 3,
    label: MATURITY_DEFINITION.MATURITY3,
    color: "#F0E489",
  },
  {
    value: 4,
    label: MATURITY_DEFINITION.MATURITY4,
    color: "#89F095",
  },
  {
    value: 5,
    label: MATURITY_DEFINITION.MATURITY5,
    color: "#89D0F0",
  },
];

const EvaluationSlider = styled(Slider)(({ theme }) => ({
  height: 3,
  padding: "13px 0",
  "& .MuiSlider-thumb": {
    height: 32,
    width: 32,
    backgroundColor: "#fff",
    border: "1px solid black",
    "&:hover": {
      boxShadow: "0 0 0 8px rgba(58, 133, 137, 0.16)",
    },
  },
  "& .MuiSlider-track": {
    height: 5,
  },
  "& .MuiSlider-rail": {
    color: theme.palette.mode === "dark" ? "#bfbfbf" : "#d8d8d8",
    opacity: theme.palette.mode === "dark" ? undefined : 1,
    height: 5,
  },
}));

function ThumbComponent(props: {
  [x: string]: any;
  children: any;
  className: any;
}) {
  const { children, className, ...other } = props;
  const extraClassName =
    other["data-index"] === 0 ? "first-thumb" : "second-thumb";
  return (
    <SliderThumb {...other} className={clsx(className, extraClassName)}>
      {children}
      {other["data-index"] === 0 ? <PersonPinIcon /> : <EmojiFlagsIcon />}
    </SliderThumb>
  );
}

ThumbComponent.propTypes = {
  children: PropTypes.node,
};

const getMarkColor = (value: number): string =>
  marks.find((mark) => mark.value === value)?.color || "primary";

const EvalSlider = ({
  sliderValue,
  setSliderValue,
  setUpdateToggle,
}: {
  sliderValue: [number, number];
  setSliderValue: React.Dispatch<React.SetStateAction<[number, number]>>;
  setUpdateToggle: React.Dispatch<React.SetStateAction<boolean>>;
}) => {
  const [inputValues, setInputValues] = useState<[number, number]>(sliderValue);

  useEffect(() => {
    setSliderValue(inputValues);
  }, [inputValues]);

  const handleInputChange =
    (index: number) =>
    (
      event:
        | React.FocusEvent<HTMLInputElement>
        | React.PointerEvent<Element>
        | React.KeyboardEvent<Element>,
      value: number | null
    ) => {
      if (value !== null) {
        const newValues = [...inputValues];
        newValues[index] = value;
        if (newValues[0] > newValues[1]) {
          setInputValues([newValues[1], newValues[1]] as [number, number]);
          return;
        }
        setInputValues([newValues[0], newValues[1]] as [number, number]);
        setUpdateToggle(true);
      }
    };

  const handleSliderChange = (event: Event, newValue: number | number[]) => {
    const newValues = newValue as [number, number];
    setSliderValue(newValues);
    setInputValues(newValues);
    setUpdateToggle(true);
  };

  const sliderColor = useMemo(
    () => getMarkColor(sliderValue[0]),
    [sliderValue]
  );

  const valuetext = (value: number) => `${value}`;

  return (
    <>
      <Box sx={{ m: 3 }}>
        <Grid container spacing={5} alignItems="center">
          <Grid item>
            <Box sx={{ mb: 8 }}>
              <NumberInput
                value={inputValues[0]}
                onChange={(event, value) => handleInputChange(0)(event, value)}
              />
            </Box>
          </Grid>
          <Grid item xs>
            <EvaluationSlider
              getAriaLabel={(index) =>
                index === 0 ? "Minimum maturity" : "Maximum maturity"
              }
              slots={{ thumb: ThumbComponent }}
              value={inputValues}
              onChange={handleSliderChange}
              defaultValue={inputValues}
              getAriaValueText={valuetext}
              valueLabelDisplay="on"
              disableSwap
              step={1}
              marks={marks}
              min={-2}
              max={5}
              tabIndex={-1}
              style={{ color: sliderColor }}
              valueLabelFormat={(_, value2) => (
                <div>{value2 === 0 ? "Oppnådd" : "Ønsket"}</div>
              )}
            />
          </Grid>
          <Grid item>
            <Box sx={{ mb: 8 }}>
              <NumberInput
                value={inputValues[1]}
                onChange={(event, value) => handleInputChange(1)(event, value)}
              />
            </Box>
          </Grid>
        </Grid>
      </Box>
    </>
  );
};

export default EvalSlider;
