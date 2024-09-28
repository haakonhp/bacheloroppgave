import { Card, CardContent, Stack, Typography } from "@mui/material";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import { useState } from "react";

const MaturityDetail = ({
  description,
  color,
}: {
  description: string;
  color: string;
}) => {
  const [toggle, setToggle] = useState<boolean>(false);

  const typographyStyle = {
    color: color,
    overflow: "hidden",
    textOverflow: "ellipsis",
    whiteSpace: toggle ? "normal" : "nowrap",
    width: "100%",
    maxWidth: "100%",
  };

  return (
    <>
      <Card sx={{ bgcolor: "#efefef", padding: "10px", width: "100%" }}>
        <CardContent>
          <Stack direction={"row"} spacing={1}>
            <Typography sx={typographyStyle}>{description}</Typography>

            {toggle ? (
              <KeyboardArrowUpIcon
                sx={{ cursor: "pointer" }}
                onClick={() => setToggle(false)}
              />
            ) : (
              <KeyboardArrowDownIcon
                sx={{ cursor: "pointer" }}
                onClick={() => setToggle(true)}
              />
            )}
          </Stack>
        </CardContent>
      </Card>
    </>
  );
};

export default MaturityDetail;
