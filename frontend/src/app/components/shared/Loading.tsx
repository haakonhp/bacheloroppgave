import { Container } from "@mui/material";
import React from "react";
import { HashLoader } from "react-spinners";

const Loading = () => {
  return (
    <>
      <Container
        sx={{ display: "flex", justifyContent: "center", marginTop: 30 }}
      >
        <HashLoader
          color={"#357EDD"}
          loading={true}
          size={150}
          aria-label="Loading Spinner"
          data-testid="loader"
          speedMultiplier={2}
        />
      </Container>
    </>
  );
};

export default Loading;
