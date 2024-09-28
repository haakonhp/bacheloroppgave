import { render, fireEvent, screen } from "@testing-library/react";
import EvalSlider from "../EvalSlider";
import { ThemeProvider, createTheme } from "@mui/material/styles";

const mockSetSliderValue = jest.fn();
const mockSetUpdateToggle = jest.fn();

describe("slider component gets right input", () => {
  let slider1: HTMLInputElement, slider2: HTMLInputElement;

  const renderComponent = (values: [number, number] = [1, 4]) =>
    render(
      <ThemeProvider theme={createTheme()}>
        <EvalSlider
          sliderValue={values}
          setSliderValue={mockSetSliderValue}
          setUpdateToggle={mockSetUpdateToggle}
        />
      </ThemeProvider>
    );

  const getSliders = () => {
    slider1 = screen.getByRole("slider", {
      name: "Minimum maturity",
    }) as HTMLInputElement;
    slider2 = screen.getByRole("slider", {
      name: "Maximum maturity",
    }) as HTMLInputElement;
  };

  beforeEach(() => {
    renderComponent();
    getSliders();
  });

  test("renders slider with correct initial values", () => {
    expect(slider1.value).toBe("1");
    expect(slider2.value).toBe("4");
  });

  test("should not render values on slider with different initial values", () => {
    expect(slider1.value).not.toBe("2");
    expect(slider2.value).not.toBe("3");
  });

  test("slider updates correctly when changed", () => {
    fireEvent.change(slider1, { target: { value: "3" } });
    fireEvent.change(slider2, { target: { value: "5" } });
    expect(slider1.value).toBe("3");
    expect(slider2.value).toBe("5");
  });

  test("slider should not show incorrect data on change", () => {
    fireEvent.change(slider1, { target: { value: "3" } });
    fireEvent.change(slider2, { target: { value: "5" } });
    expect(slider1.value).not.toBe("1");
    expect(slider2.value).not.toBe("4");
  });

  test("slider1 should not be higher than slider2", () => {
    fireEvent.change(slider1, { target: { value: "5" } });
    fireEvent.change(slider2, { target: { value: "3" } });
    expect(slider1.value).not.toBe("5");
    expect(slider2.value).not.toBe("3");
  });

  test("slider1 should equal slider2 if value is higher", () => {
    fireEvent.change(slider1, { target: { value: "5" } });
    fireEvent.change(slider2, { target: { value: "3" } });
    expect(slider1.value).toBe(slider2.value);
  });
});
