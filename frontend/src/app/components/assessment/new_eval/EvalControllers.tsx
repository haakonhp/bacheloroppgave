import { Avatar, Box, Stack, Typography } from "@mui/material";
import React from "react";
import AssignmentIcon from "@mui/icons-material/Assignment";
import { green } from "@mui/material/colors";

const EvalControllers = ({
  controlsLength,
}: {
  controlsLength: number | undefined;
}) => {
  return (
    <>
      <Box>
        <Stack direction="row">
          <Avatar sx={{ bgcolor: green[500] }} variant="rounded">
            <AssignmentIcon />
          </Avatar>
          <Typography sx={{ ml: 3 }} variant="h4">
            Kontroller: {controlsLength === undefined ? 0 : controlsLength}
          </Typography>
        </Stack>
      </Box>
    </>
  );
};

export default EvalControllers;
