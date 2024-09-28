import React, { useState } from "react";
import { Typography } from "@mui/material";
import AssessmentCard from "./AssessmentCard";
import MaturityInfo from "./maturityInfo/MaturityInfo";
import InfiniteScroll from "react-infinite-scroll-component";
import { Answer, NewAnswer } from "@/types/AssessmentType";

const ControlsCard = ({
  categoryName,
  controls,
  addAnswerToList,
}: {
  categoryName: string;
  controls: Answer[];
  addAnswerToList: (newAnswer: NewAnswer) => void;
}) => {
  const [itemsToShow, setItemsToShow] = useState(4);

  const categorizedData = controls.filter(
    (item) => item.control.cyberDefenseFunction === categoryName
  );

  const loadMoreItems = () => {
    setItemsToShow(itemsToShow + 4);
  };

  const visibleItems = categorizedData.slice(0, itemsToShow);

  return (
    <>
      <Typography>
        {categorizedData.length} kontroller i denne kategorien
      </Typography>
      <InfiniteScroll
        dataLength={visibleItems.length}
        next={loadMoreItems}
        hasMore={visibleItems.length < categorizedData.length}
        loader={<h4>Loading...</h4>}
      >
        {visibleItems.map((item, index) => (
          <AssessmentCard
            key={index}
            control={item.control.controlOverview}
            chipColor={item.control.cyberDefenseFunctionColor}
            categoryNr={item.control.controlID}
            controlDescription={item.control.details}
          >
            <MaturityInfo control={item} addAnswerToList={addAnswerToList} />
          </AssessmentCard>
        ))}
      </InfiniteScroll>
    </>
  );
};

export default ControlsCard;
