import * as React from "react";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import ControlsCard from "./ControlsCard";
import AssessmentType, { Answer, NewAnswer } from "@/types/AssessmentType";
import {
  Dispatch,
  SetStateAction,
  SyntheticEvent,
  useEffect,
  useState,
} from "react";
import { Alert, Snackbar } from "@mui/material";

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function CustomTabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
    </div>
  );
}

function a11yProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

export default function CategoryTabs({
  categoryMap,
  controls,
  newAnswerList,
  open,
  saveAnswers,
  setOpen,
  setAssessment,
  setNewAnswerList,
}: {
  categoryMap: Set<unknown>;
  controls: Answer[];
  newAnswerList: NewAnswer[];
  open: boolean;
  saveAnswers: () => Promise<void>;
  setOpen: Dispatch<SetStateAction<boolean>>;
  setAssessment: Dispatch<SetStateAction<AssessmentType | null>>;
  setNewAnswerList: Dispatch<SetStateAction<NewAnswer[]>>;
}) {
  const [value, setValue] = useState(0);

  const handleClose = (
    event?: React.SyntheticEvent | Event,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  const updateAnswersInState = (newAnswer: NewAnswer) => {
    setAssessment((prevAssessment) => {
      if (!prevAssessment) return null;

      const updatedAnswers = prevAssessment.answers.map((answer) =>
        answer.id === newAnswer.id ? { ...answer, ...newAnswer } : answer
      );

      return { ...prevAssessment, answers: updatedAnswers };
    });
  };

  const addAnswerToList = (newAnswer: NewAnswer) => {
    setNewAnswerList((prevList) => {
      const filteredList = prevList.filter(
        (answer) => answer.id !== newAnswer.id
      );
      return [...filteredList, newAnswer];
    });

    updateAnswersInState(newAnswer);
  };

  useEffect(() => {
    const intervalId = setInterval(async () => {
      if (newAnswerList.length === 0) {
        return null;
      } else {
        saveAnswers();
      }
    }, 5000);

    return () => clearInterval(intervalId);
  }, [newAnswerList]);

  const handleChange = (event: SyntheticEvent, newValue: number) => {
    saveAnswers();
    setValue(newValue);
  };

  return (
    <Box sx={{ width: "100%" }}>
      <Box sx={{ borderBottom: 2, borderColor: "divider" }}>
        <Tabs
          value={value}
          onChange={handleChange}
          aria-label="basic tabs example"
          variant="scrollable"
          scrollButtons="auto"
        >
          {Array.from(categoryMap).map((category, index) => (
            <Tab key={index} label={category as string} {...a11yProps(index)} />
          ))}
        </Tabs>
      </Box>
      {Array.from(categoryMap).map((category, index) => (
        <CustomTabPanel key={index} value={value} index={index}>
          <ControlsCard
            categoryName={category as string}
            controls={controls}
            addAnswerToList={addAnswerToList}
          />
        </CustomTabPanel>
      ))}
      <Snackbar open={open} autoHideDuration={2000} onClose={handleClose}>
        <Alert
          onClose={handleClose}
          severity="info"
          variant="filled"
          sx={{ width: "100%" }}
        >
          Auto lagret
        </Alert>
      </Snackbar>
    </Box>
  );
}
