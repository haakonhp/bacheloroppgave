import { Button, Menu, MenuItem } from "@mui/material";
import RemoveRedEyeIcon from "@mui/icons-material/RemoveRedEye";
import React from "react";

const ViewMode = ({
  view,
  setView,
}: {
  view: number;
  setView: React.Dispatch<React.SetStateAction<number>>;
}) => {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = (viewValue: number) => {
    setView(viewValue);
    setAnchorEl(null);
  };

  return (
    <>
      <Button
        id="basic-button"
        variant="outlined"
        aria-controls={open ? "basic-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={open ? "true" : undefined}
        onClick={handleClick}
      >
        <RemoveRedEyeIcon sx={{ mr: 2 }} />
        Visning
      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={() => handleClose(view)}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <MenuItem onClick={() => handleClose(1)}>Liste</MenuItem>
        <MenuItem onClick={() => handleClose(2)}>Kolonner</MenuItem>
        <MenuItem onClick={() => handleClose(3)}>Rader</MenuItem>
      </Menu>
    </>
  );
};

export default ViewMode;
