import { render, screen } from "@testing-library/react";
import AssessmentCard from "../AssessmentCard";
import "@testing-library/jest-dom";
import { MockControls } from "../MockControls";

describe("component renders the right data ", () => {
  test("renders the overview text", () => {
    const { container } = render(
      <AssessmentCard
        control={MockControls[0].control.controlOverview}
        chipColor={""}
        categoryNr={MockControls[0].control.controlID}
        controlDescription={""}
        // eslint-disable-next-line react/no-children-prop
        children={undefined}
      />
    );
    const controlOverview = screen.getByText(
      "Identifiser virksomhetens strategi og prioriterte mål"
    );
    const controlId = screen.getByText("1.1.1");

    expect(controlOverview).toBeInTheDocument();
    expect(controlId).toBeInTheDocument();
  });

  test("should not show this overview text", () => {
    const { container } = render(
      <AssessmentCard
        control={MockControls[0].control.controlOverview}
        chipColor={""}
        categoryNr={MockControls[0].control.controlID}
        controlDescription={""}
        // eslint-disable-next-line react/no-children-prop
        children={undefined}
      />
    );
    const controlOverview = screen.queryByText("Another type of overview");
    const controlId = screen.queryByText("1.1.2");

    expect(controlOverview).not.toBeInTheDocument();
    expect(controlId).not.toBeInTheDocument();
  });
});
