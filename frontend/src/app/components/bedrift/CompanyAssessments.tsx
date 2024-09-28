"use client";
import React, { useEffect, useState } from "react";
import { format } from "date-fns";

import {
  Box,
  Button,
  Chip,
  Divider,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import CircularProgress, {
  CircularProgressProps,
} from "@mui/material/CircularProgress";
import { useRouter } from "next/navigation";
import AssessmentType from "@/types/AssessmentType";
import DeleteIcon from "@mui/icons-material/Delete";
import DialogComponent from "../shared/Dialog";
import CancelIcon from "@mui/icons-material/Cancel";

const CompanyAssessments = ({ companyId }: { companyId: string }) => {
  const [evaluations, setEvaluations] = useState<AssessmentType[] | null>(null);
  const [openDelete, setOpenDelete] = useState<null | string>(null);

  const router = useRouter();

  const fetchEvaluations = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/evaluations/corporation/${companyId}`
      );
      const result = await response.json();
      setEvaluations(result);
    } catch (error) {
      console.error("Error fetching company:", error);
    }
  };

  const deleteAssessment = async (id: string) => {
    try {
      const response = await fetch(`http://localhost:8080/evaluations/${id}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });
      fetchEvaluations();
    } catch (error) {
      console.error("Delete failed, showing error Snackbar:", error);
    }
  };

  useEffect(() => {
    fetchEvaluations();
  }, [companyId]);

  if (evaluations === null) {
    return <p>Loading...</p>;
  } else if (evaluations.length > 0) {
    return (
      <>
        <Typography variant="h5">Tilstandsanalyser</Typography>
        <br />
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Tilstandsanalyse</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Opprettet</TableCell>
                <TableCell>Sist utført</TableCell>
                <TableCell>Besvart</TableCell>
                <TableCell>Progress</TableCell>
                <TableCell>Slett</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {evaluations.map((row) => (
                <TableRow
                  key={row.id}
                  onClick={() => {
                    if (openDelete === null) {
                      router.push(`/tilstandsanalyse/${row.id}`);
                    }
                  }}
                  sx={{
                    "&:last-child td, &:last-child th": { border: 0 },
                    "&:hover": {
                      background: "#f9f9f9",
                      cursor: "pointer",
                    },
                  }}
                >
                  <TableCell component="th" scope="row">
                    {row.name}
                  </TableCell>
                  <TableCell>
                    {row.completedAnswerCount === 0 && (
                      <Chip size="small" label="Ikke startet"></Chip>
                    )}
                    {row.completedAnswerCount === row.answerCount && (
                      <Chip
                        size="small"
                        color="success"
                        label="Fullført"
                      ></Chip>
                    )}
                    {row.completedAnswerCount > 0 &&
                      row.completedAnswerCount < row.answerCount && (
                        <Chip
                          size="small"
                          color="primary"
                          label="Pågående"
                        ></Chip>
                      )}
                  </TableCell>
                  <TableCell>
                    {format(new Date(row.createdAt), "dd.MM.yyyy")}
                  </TableCell>
                  <TableCell>
                    {row.updatedAt &&
                      format(new Date(row.updatedAt), "dd.MM.yyyy")}
                  </TableCell>
                  <TableCell>
                    {`${row.completedAnswerCount}/${row.answerCount}`}
                  </TableCell>
                  <TableCell>
                    {row.answerCount !== 0 ? (
                      <CircularProgressWithLabel
                        value={
                          (row.completedAnswerCount / row.answerCount) * 100
                        }
                      />
                    ) : (
                      <CircularProgressWithLabel value={0} />
                    )}
                  </TableCell>
                  <TableCell>
                    <>
                      <Button
                        color="error"
                        variant="outlined"
                        onClick={(event) => {
                          event.stopPropagation();
                          setOpenDelete(row.id);
                        }}
                      >
                        <DeleteIcon />
                      </Button>
                      <DialogComponent
                        title={"Evaluering"}
                        open={openDelete === row.id}
                        setOpen={() => setOpenDelete(null)}
                      >
                        <>
                          <p>
                            Er du sikker at du vil slette denne
                            tilstandsanalysen?
                          </p>
                          <Divider
                            style={{ marginTop: 10, marginBottom: 10 }}
                          />
                          <Button
                            color="error"
                            onClick={() => {
                              deleteAssessment(row.id);
                              setOpenDelete(null);
                            }}
                            startIcon={<DeleteIcon />}
                          >
                            Ja
                          </Button>
                          <Button
                            onClick={() => setOpenDelete(null)}
                            startIcon={<CancelIcon />}
                          >
                            Nei
                          </Button>
                        </>
                      </DialogComponent>
                    </>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </>
    );
  } else {
    return (
      <p>Fant ingen tilstandsanalyser på denne bedriften. Lag en ny analyse.</p>
    );
  }
};
export default CompanyAssessments;

function CircularProgressWithLabel(
  props: CircularProgressProps & { value: number }
) {
  return (
    <Box sx={{ position: "relative", display: "inline-flex" }}>
      <CircularProgress variant="determinate" {...props} />
      <Box
        sx={{
          top: 0,
          left: 0,
          bottom: 0,
          right: 0,
          position: "absolute",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <Typography
          variant="caption"
          component="div"
          color="text.secondary"
        >{`${Math.round(props.value)}%`}</Typography>
      </Box>
    </Box>
  );
}
