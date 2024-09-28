import { Stack, Typography } from "@mui/material";
import Chip from "@mui/material/Chip";
import React from "react";

const CompaniesAction = ({
  view,
  setView,
}: {
  view: number;
  setView: React.Dispatch<React.SetStateAction<number>>;
}) => {
  return (
    <>
      <Typography>Visningsform:</Typography>

      <Stack direction="row" spacing={1}>
        <Chip
          label="Liste"
          color={view === 1 ? "primary" : undefined}
          variant="outlined"
          onClick={() => setView(1)}
        />
        <Chip
          label="Kort"
          color={view === 2 ? "primary" : undefined}
          variant="outlined"
          onClick={() => setView(2)}
        />
        <Chip
          label="Lang"
          color={view === 3 ? "primary" : undefined}
          variant="outlined"
          onClick={() => setView(3)}
        />
      </Stack>
    </>
  );
};

export default CompaniesAction;
