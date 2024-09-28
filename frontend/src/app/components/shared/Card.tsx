import React, { ReactNode } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

const CardComponent = ({
  title,
  children,
}: {
  title: string;
  children: ReactNode;
}) => {
  return (
    <>
      <Card>
        <CardContent>
          <Typography sx={{ fontSize: 20 }} gutterBottom>
            {title}
          </Typography>

          {children}
        </CardContent>
      </Card>
    </>
  );
};

export default CardComponent;
