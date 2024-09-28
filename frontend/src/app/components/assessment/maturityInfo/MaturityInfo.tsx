"use client";
import React, { useEffect, useState } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import { Divider, Stack } from "@mui/material";
import MaturityDetail from "./MaturityDetail";
import { Answer, NewAnswer } from "@/types/AssessmentType";
import EvalSlider from "../../slider/EvalSlider";

const MaturityInfo = ({
  control,
  addAnswerToList,
}: {
  control: Answer;
  addAnswerToList: (newAnswer: NewAnswer) => void;
}) => {
  const [sliderValue, setSliderValue] = useState<[number, number]>([
    control.value,
    control.desiredValue === -1 ? 5 : control.desiredValue,
  ]);

  const [updateToggle, setUpdateToggle] = useState<boolean>(false);

  const currentColor = "#000000";
  const desiredColor = "#000000";

  const [maturityInfo, setMaturityInfo] = useState({
    maturityDetail: "",
    color: "#000000",
  });

  useEffect(() => {
    if (!updateToggle) {
      return;
    } else {
      const newAnswer: NewAnswer = {
        id: control.id,
        value: sliderValue[0],
        lastUpdatedAt: new Date().toISOString(),
        desiredValue: sliderValue[1],
      };
      addAnswerToList(newAnswer);
      setUpdateToggle(false);
    }
  }, [sliderValue]);

  useEffect(() => {
    const setSliderData = () => {
      if (sliderValue[1] === -2) {
        setMaturityInfo({
          maturityDetail: "Denne kontrollen tas ikke med i vurdering.",
          color: currentColor,
        });
      } else if (sliderValue[1] === -1) {
        setMaturityInfo({
          maturityDetail: "Ingen svar registrert.",
          color: currentColor,
        });
      } else if (sliderValue[1] === 0) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel0,
          color: currentColor,
        });
      } else if (sliderValue[1] === 1) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel1,
          color: currentColor,
        });
      } else if (sliderValue[1] === 2) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel2,
          color: currentColor,
        });
      } else if (sliderValue[1] === 3) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel3,
          color: currentColor,
        });
      } else if (sliderValue[1] === 4) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel4,
          color: currentColor,
        });
      } else if (sliderValue[1] === 5) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel5,
          color: currentColor,
        });
      }
    };

    setSliderData();
  }, [sliderValue[1]]);

  useEffect(() => {
    const setSliderData = () => {
      if (sliderValue[0] === -2) {
        setMaturityInfo({
          maturityDetail: "Denne kontrollen tas ikke med i vurdering.",
          color: desiredColor,
        });
      } else if (sliderValue[0] === -1) {
        setMaturityInfo({
          maturityDetail: "Ingen svar registrert.",
          color: desiredColor,
        });
      } else if (sliderValue[0] === 0) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel0,
          color: desiredColor,
        });
      } else if (sliderValue[0] === 1) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel1,
          color: desiredColor,
        });
      } else if (sliderValue[0] === 2) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel2,
          color: desiredColor,
        });
      } else if (sliderValue[0] === 3) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel3,
          color: desiredColor,
        });
      } else if (sliderValue[0] === 4) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel4,
          color: desiredColor,
        });
      } else if (sliderValue[0] === 5) {
        setMaturityInfo({
          maturityDetail: control.control.maturityLevel5,
          color: desiredColor,
        });
      }
    };

    setSliderData();
  }, [sliderValue[0]]);

  return (
      <>
        <Card sx={{ bgcolor: "#FAFAFA", width: "100%" }}>
          <CardContent>
            <Stack spacing={3}>
              <Divider sx={{ my: 4 }} />
              {sliderValue ? (
                  <EvalSlider
                      sliderValue={sliderValue}
                      setSliderValue={setSliderValue}
                      setUpdateToggle={setUpdateToggle}
                  />
              ) : (
                  "Laster..."
              )}
              <MaturityDetail
                  description={maturityInfo.maturityDetail || "Mangler info."}
                  color={maturityInfo.color}
              />
            </Stack>
          </CardContent>
        </Card>
      </>
  );
};

export default MaturityInfo;
