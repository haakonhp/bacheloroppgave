import { Image, StyleSheet } from "@react-pdf/renderer";

export const DiagramImage = ({
  image,
  imgWidth,
  imgHeight,
}: {
  image?: any;
  imgWidth: number;
  imgHeight: number;
}) => {
  const styles = StyleSheet.create({
    image: {
      alignItems: "center",
      width: imgWidth,
      height: imgHeight,
    },
  });

  if (!image) {
    return null;
  } else {
    return (
      <>
        <Image style={styles.image} source={image} />
      </>
    );
  }
};
