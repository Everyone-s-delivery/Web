import * as React from 'react';

interface LogoProps {
  width?: string;
  height?: string;
}
const Logo = (props: LogoProps) => {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width={props.width}
      height={props.height}
      viewBox="0 0 1200.000000 1200.000000"
    >
      <path
        d="M5575 8831c-225-131-601-349-835-484l-425-246V6148l390-225c959-554 1288-743 1295-743 5 0 368 207 807 461 439 253 817 472 841 485l42 25v1948l-42 25c-24 13-402 232-841 485-439 254-804 461-810 460-7 0-196-108-422-238zm1195-374l765-442V6235l-765-443c-439-253-771-440-780-437-13 5-348 199-1230 709l-295 171v1780l295 171c856 495 1236 714 1240 714 3 0 349-199 770-443z"
        transform="matrix(.1 0 0 -.1 0 1200)"
      />
      <path
        d="M5546 7907c-13-10-16-20-12-35 6-19 15-22 56-22 49 0 49 0 55-37 4-21 9-51 12-68 10-57 45-136 79-180l34-45h-165c-150 0-165-2-174-19-16-29 7-50 59-53l45-3 8-70c13-124 60-236 129-311l41-44h-200c-211 0-239-6-231-46 3-16 14-20 73-24l70-5 6-72c10-106 26-168 69-258 65-136 157-220 288-264 79-27 314-30 407-6 206 54 349 254 373 524l7 76 70 5c59 4 70 8 73 24 8 40-20 46-231 46h-200l41 44c69 75 116 187 129 311l8 70 45 3c52 3 75 24 59 53-9 17-24 19-174 19h-165l34 45c37 48 70 127 81 191 17 101 12 94 65 94 41 0 50 3 56 22 14 47 2 48-466 48-347 0-440-3-454-13zm734-95c0-92-59-209-131-262-41-29-46-30-149-30-102 0-109 1-147 29-47 34-75 71-103 136-20 48-41 151-32 160 3 3 131 5 284 5h278v-38zm110-397c0-92-52-232-111-300-19-21-54-51-78-67-44-28-47-28-201-28s-157 0-201 28c-24 16-59 46-78 67-59 68-111 208-111 300v35h780v-35zm106-540c-19-211-117-373-266-444-53-25-62-26-230-26-173 0-176 0-235 29-147 72-242 233-261 441l-7 75h1006l-7-75zM6180 3700v-630h150v596c0 328 2 599 5 602 12 12 4 42-14 52-11 5-47 10-80 10h-61v-630zM7948 4313c-2-10-4-129-3-265v-248h-125v200c0 140-3 200-11 200-7 0-5 10 5 25 20 30 20 30 0 49-11 11-35 16-80 16h-64V3139l73 3 72 3 3 258 2 258 63-3 62-3v-585h156l-1 617c-1 421-5 621-12 630-6 8-34 13-73 13-51 0-63-3-67-17zM9327 4324c-4-4-7-166-7-361v-353h-760v-140h380c373 0 380 0 380-20s-7-20-380-20h-380v-330h950v140h-400c-393 0-400 0-400 20s7 20 380 20h380l1 163c0 89 0 222-1 295l-1 132h201v150h-199l-3 118c-2 65 1 125 7 135 19 34-6 52-76 55-36 2-68 0-72-4zM3980 4030v-250l463 2 462 3v140l-388 3-388 2 3 103 3 102 385 5 385 5v68l1 67h-926v-250zM5619 4239c-121-13-216-75-265-174-24-50-26-64-22-139 4-69 9-92 35-137 61-109 183-168 325-156 149 12 223 56 277 160 30 59 34 75 34 149 0 66-4 92-21 125-68 132-190 189-363 172zm159-168c50-26 72-64 72-128 0-95-56-156-156-169-203-25-299 223-120 308 53 24 144 20 204-11z"
        transform="matrix(.1 0 0 -.1 0 1200)"
      />
      <path
        d="M8460 3944v-296l148 7c271 11 633 57 665 84 8 6 10 31 7 73-6 74 11 71-180 35-143-27-324-47-428-47h-63l3 148 3 147 243 1c133 1 247 2 252 3 6 0 10 32 10 71v70h-660v-296zM6970 3795v-415h571l-1 383c-1 210 2 390 5 400 13 33-13 47-86 47h-69v-280h-269l-3 106c-2 58 1 112 5 120 5 8 5 23 2 34-6 18-15 20-81 20h-74v-415zm420-145v-131l-132 3-133 3-3 128-3 127h271v-130zM2572 3918l3-283 448-3 447-2v570h-900l2-282zm748-3v-145l-297 2-298 3-3 143-3 142h601v-145zM3830 3610v-70h530v-470h150v470h520v140H3830v-70zM2950 3485v-125h-530v-150h1200v150h-520v85c0 51-4 85-10 85s-3 11 6 25c26 40 5 55-77 55h-69v-125zM5670 3512c-245-19-446-36-448-37-10-9 34-127 53-145 22-21 25-21 77-6 44 13 306 39 726 72l82 7v73c0 70-1 74-22 73-13-1-223-17-468-37z"
        transform="matrix(.1 0 0 -.1 0 1200)"
      />
    </svg>
  );
};

Logo.defaultProps = {
  width: '300px',
  height: '300px',
};
export default Logo;