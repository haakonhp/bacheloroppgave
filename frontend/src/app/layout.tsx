import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import PersistentDrawerLeft from "./components/shared/Sidebar";
const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Saphêneia",
  description: "Intern assessment for Dunamis Technology",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body className={inter.className}>
        <PersistentDrawerLeft>{children}</PersistentDrawerLeft>

        {/* <Footer /> */}
      </body>
    </html>
  );
}
