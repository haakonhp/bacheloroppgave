import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import ControlsCard from "../ControlsCard";
import { MockControls } from "../MockControls";

const mockAddAnswerToList = jest.fn();

describe("Controls amount testing ", () => {
  test("renders the right amount of controls", () => {
    const mockCategoryName = "Kategori";
    const filteredMockControls = MockControls.filter(
      (control) => control.control.cyberDefenseFunction === mockCategoryName
    );

    const { container } = render(
      <ControlsCard
        categoryName={mockCategoryName}
        controls={MockControls}
        addAnswerToList={mockAddAnswerToList}
      />
    );

    const controlsAmount = screen.getByText(
      `${filteredMockControls.length} kontroller i denne kategorien`
    );
    expect(controlsAmount).toBeInTheDocument();
    expect(mockAddAnswerToList).not.toHaveBeenCalled();
  });

  test("renders the wrong amount of controls", () => {
    const mockCategoryName = "Kategori";

    const { container } = render(
      <ControlsCard
        categoryName={mockCategoryName}
        controls={MockControls}
        addAnswerToList={mockAddAnswerToList}
      />
    );

    const controlsAmount = screen.queryByText(
      `4 kontroller i denne kategorien`
    );
    expect(controlsAmount).not.toBeInTheDocument();
    expect(mockAddAnswerToList).not.toHaveBeenCalled();
  });
});
