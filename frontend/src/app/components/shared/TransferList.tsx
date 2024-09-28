import {
  ToggleButtonGroup,
  ToggleButton,
  Typography,
  Box,
} from "@mui/material";
import GavelIcon from "@mui/icons-material/Gavel";

export default function ToggleButtonsMultiple({
  sourceFrameworks,
  selectedFrameworks,
  setSelectedFrameworks,
}: {
  sourceFrameworks: string[];
  selectedFrameworks: string[];
  setSelectedFrameworks: React.Dispatch<React.SetStateAction<[] | string[]>>;
}) {
  const handleFormat = (
    event: React.MouseEvent<HTMLElement>,
    newFormats: string[]
  ) => {
    setSelectedFrameworks(newFormats);
  };

  if (sourceFrameworks.length > 0) {
    return (
      <Box sx={{ height: 300, overflow: "hidden", overflowY: "scroll" }}>
        <ToggleButtonGroup
          orientation="vertical"
          value={selectedFrameworks}
          onChange={handleFormat}
          aria-label="text formatting"
        >
          {sourceFrameworks.map((item, index) => (
            <ToggleButton
              key={index}
              color="success"
              value={item}
              aria-label={item}
            >
              <GavelIcon />
              <Typography sx={{ ml: 1 }}>{item}</Typography>
            </ToggleButton>
          ))}
        </ToggleButtonGroup>
      </Box>
    );
  } else {
    return null;
  }
}
