// icontify로 대체하기
interface PawIconSVGProps {
  width: string;
  height: string;
  viewBox: string;
  fill: string;
}

export const PawIconSVG = ({ width, height, viewBox, fill }: PawIconSVGProps) => {
  return (
    <svg
      width={width}
      height={height}
      viewBox={viewBox}
      fill='none'
      xmlns='http://www.w3.org/2000/svg'
    >
      <path
        d='M63.75 27.8906C63.75 25.3164 64.5198 22.7999 65.962 20.6595C67.4042 18.5191 69.454 16.8509 71.8523 15.8658C74.2506 14.8806 76.8896 14.6229 79.4356 15.1251C81.9816 15.6273 84.3202 16.8669 86.1558 18.6872C87.9914 20.5075 89.2414 22.8266 89.7478 25.3514C90.2543 27.8762 89.9943 30.4932 89.0009 32.8715C88.0075 35.2498 86.3253 37.2826 84.1669 38.7127C82.0085 40.1429 79.4709 40.9063 76.875 40.9063C73.3978 40.894 70.0666 39.5188 67.6079 37.0805C65.1491 34.6423 63.7624 31.3388 63.75 27.8906V27.8906ZM33.75 50.2031C33.75 47.6289 32.9802 45.1124 31.538 42.972C30.0959 40.8316 28.046 39.1634 25.6477 38.1783C23.2494 37.1931 20.6104 36.9354 18.0644 37.4376C15.5184 37.9398 13.1798 39.1794 11.3442 40.9997C9.50867 42.82 8.25863 45.1391 7.7522 47.6639C7.24577 50.1887 7.50569 52.8057 8.49909 55.184C9.49249 57.5623 11.1748 59.5951 13.3331 61.0252C15.4915 62.4554 18.0291 63.2188 20.625 63.2188C24.1022 63.2065 27.4334 61.8313 29.8921 59.393C32.3509 56.9548 33.7377 53.6513 33.75 50.2031ZM43.125 40.9063C45.7209 40.9063 48.2585 40.1429 50.4169 38.7127C52.5753 37.2826 54.2575 35.2498 55.2509 32.8715C56.2443 30.4932 56.5042 27.8762 55.9978 25.3514C55.4914 22.8266 54.2414 20.5075 52.4058 18.6872C50.5702 16.8669 48.2316 15.6273 45.6856 15.1251C43.1396 14.6229 40.5006 14.8806 38.1023 15.8658C35.704 16.8509 33.6542 18.5191 32.212 20.6595C30.7698 22.7999 30 25.3164 30 27.8906C30.0124 31.3388 31.3991 34.6423 33.8579 37.0805C36.3166 39.5188 39.6478 40.894 43.125 40.9063V40.9063ZM87.7031 69.1688C85.7976 68.1302 84.1172 66.7294 82.7579 65.0465C81.3986 63.3635 80.3871 61.4314 79.7813 59.3606C78.5497 55.1007 75.9534 51.3542 72.3847 48.6872C68.8159 46.0203 64.4687 44.5778 60 44.5778C55.5314 44.5778 51.1841 46.0203 47.6154 48.6872C44.0466 51.3542 41.4503 55.1007 40.2188 59.3606C38.9972 63.53 36.1677 67.054 32.3438 69.1688C28.671 71.1346 25.7646 74.2594 24.085 78.0482C22.4053 81.8371 22.0484 86.0733 23.0708 90.086C24.0931 94.0987 26.4363 97.6585 29.7292 100.201C33.0221 102.744 37.0764 104.125 41.25 104.125C43.7438 104.123 46.2127 103.633 48.5156 102.684C55.8509 99.6705 64.0899 99.6538 71.4375 102.638C73.7483 103.619 76.2361 104.125 78.75 104.125C82.9276 104.136 86.9891 102.762 90.29 100.223C93.5909 97.6836 95.9421 94.1239 96.9705 90.1086C97.9989 86.0933 97.6456 81.8524 95.9667 78.0589C94.2878 74.2653 91.3795 71.1365 87.7031 69.1688V69.1688ZM99.375 37.1875C96.7791 37.1875 94.2415 37.9509 92.0832 39.381C89.9248 40.8112 88.2425 42.844 87.2491 45.2223C86.2557 47.6006 85.9958 50.2176 86.5022 52.7424C87.0086 55.2671 88.2587 57.5863 90.0942 59.4066C91.9298 61.2268 94.2685 62.4665 96.8145 62.9687C99.3605 63.4709 101.999 63.2131 104.398 62.228C106.796 61.2429 108.846 59.5746 110.288 57.4342C111.73 55.2938 112.5 52.7774 112.5 50.2031C112.488 46.7549 111.101 43.4515 108.642 41.0132C106.183 38.575 102.852 37.1998 99.375 37.1875V37.1875Z'
        fill={fill}
      />
    </svg>
  );
};
