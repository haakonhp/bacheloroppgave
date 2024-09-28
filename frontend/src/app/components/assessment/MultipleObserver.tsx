// MultipleObserver.js
import { useInView } from "react-intersection-observer";

const MultipleObserver = ({ children }: { children: any }) => {
  const { ref, inView } = useInView({ triggerOnce: true });

  return <div ref={ref}>{inView ? <span>{children}</span> : null}</div>;
};

export default MultipleObserver;
