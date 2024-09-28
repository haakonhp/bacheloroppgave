import { Container, Dialog, DialogTitle, Divider } from "@mui/material";
import React from "react";

const DialogComponent = ({
  title,
  open,
  setOpen,
  children,
}: {
  title: string;
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  children: React.ReactElement;
}) => {
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = (value: string) => {
    setOpen(false);
  };

  return (
    <>
      <Dialog onClose={handleClose} open={open}>
        <DialogTitle>{title}</DialogTitle>
        <Container sx={{ m: 3, width: 400 }}>
          {children}
          <Divider style={{ marginTop: 10 }} />
        </Container>
      </Dialog>
    </>
  );
};

export default DialogComponent;
