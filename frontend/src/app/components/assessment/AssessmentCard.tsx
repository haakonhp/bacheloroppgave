import React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import {
  Accordion,
  AccordionSummary,
  Box,
  Stack,
  Typography,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";

const AssessmentCard = ({
  control,
  chipColor,
  categoryNr,
  controlDescription,
  children,
}: {
  control: string;
  chipColor: string;
  categoryNr: React.ReactNode;
  controlDescription: string;
  children: React.ReactNode;
}) => {
  return (
    <>
      <Card sx={{ bgcolor: "#FAFAFA", mb: 5 }}>
        <CardContent sx={{ bgcolor: "#FAFAFA" }}>
          <Accordion sx={{ bgcolor: "#efefef", padding: 2 }}>
            <AccordionSummary
              tabIndex={-1}
              sx={{ bgcolor: "#efefef" }}
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1-content"
              id="panel1-header"
            >
              <Stack direction="row" spacing={2}>
                <Box
                  sx={{
                    padding: 1,
                    backgroundColor: chipColor,
                    borderRadius: 2,
                  }}
                >
                  <Typography>{categoryNr}</Typography>
                </Box>

                <Typography sx={{ fontWeight: "bold", ml: 5 }}>
                  {control}
                </Typography>
              </Stack>
            </AccordionSummary>
            <Typography style={{ whiteSpace: "pre-line" }}>
              {controlDescription}
            </Typography>
          </Accordion>
        </CardContent>
        {children}
      </Card>
    </>
  );
};

export default AssessmentCard;
