import {
  Accordion,
  AccordionDetails,
  AccordionSlots,
  AccordionSummary,
  Box,
  Fade,
  Stack,
  Typography,
} from "@mui/material";
import React from "react";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import ControlType from "@/types/ControlType";

const MPAccordion = ({ results, header }: { results: any; header: string }) => {
  const [expanded, setExpanded] = React.useState(false);

  const handleExpansion = () => {
    setExpanded((prevExpanded) => !prevExpanded);
  };

  return (
    <>
      <Accordion
        expanded={expanded}
        onChange={handleExpansion}
        slots={{ transition: Fade as AccordionSlots["transition"] }}
        slotProps={{ transition: { timeout: 400 } }}
        sx={{
          "& .MuiAccordion-region": { height: expanded ? "auto" : 0 },
          "& .MuiAccordionDetails-root": {
            display: expanded ? "block" : "none",
          },
        }}
      >
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <Typography>{header}</Typography>
        </AccordionSummary>
        <AccordionDetails>
          {results &&
            results.mostPressing.map(
              (item: { control: ControlType }, index: any) => (
                <React.Fragment key={index}>
                  <Box
                    component="section"
                    sx={{
                      border: "2px solid #D3D3D3",
                      mb: 2,
                      boxShadow: "0px 2px 4px rgba(0, 0, 0, 0.1)",
                      borderRadius: "8px",
                    }}
                  >
                    <Stack sx={{ width: "100%" }} direction={"row"}>
                      <Box
                        sx={{
                          p: 5,
                          background: "#cedff0	",
                          alignContent: "center",
                          justifyContent: "center",
                        }}
                      >
                        <Typography variant="body1">{index + 1}</Typography>
                      </Box>
                      <Box sx={{ width: "100%", p: 3 }}>
                        <Stack>
                          <Typography variant="body1">
                            <span style={{ fontWeight: "bold" }}>
                              Kontrollnr:{" "}
                            </span>
                            {item.control.controlID}
                          </Typography>
                          <Typography variant="body1">
                            <span style={{ fontWeight: "bold" }}>Kilde: </span>
                            {item.control.source}
                          </Typography>
                          <Typography variant="body1">
                            <span style={{ fontWeight: "bold" }}>
                              Kontroll:{" "}
                            </span>
                            {item.control.controlOverview}
                          </Typography>
                          <Typography variant="body1">
                            <span style={{ fontWeight: "bold" }}>
                              Prioritet:{" "}
                            </span>
                            {item.control.priority}
                          </Typography>
                          <Typography variant="body1">
                            <span style={{ fontWeight: "bold" }}>
                              Kontrollspørsmål:{" "}
                            </span>
                            {item.control.controlQuestion}
                          </Typography>
                        </Stack>
                      </Box>
                    </Stack>
                  </Box>
                </React.Fragment>
              )
            )}
        </AccordionDetails>
      </Accordion>
    </>
  );
};

export default MPAccordion;
