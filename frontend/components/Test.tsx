import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { useState, useEffect } from 'react';

export default function Test(props: ITestProps) {
  const { test } = props;
  const [bgColor, setBgColor] = useState('');
  const router = useRouter();

  useEffect(() => {
    const hue = Math.floor(Math.random() * 96) + 25; // 25-120 (warm olive/amber range)
    const saturation = Math.floor(Math.random() * 31) + 40; // 40-70% saturation
    const lightness = Math.floor(Math.random() * 21) + 30; // 30-50% lightness
    const randomColor = `hsl(${hue}, ${saturation}%, ${lightness}%)`;
    setBgColor(randomColor);
  }, []);

  const handleClick = (e: any) => {
    e.preventDefault();
    router.push(`/tests/${test.id}/quiz`);
  };

  return (
    <>
      <Link
        href={`/tests/${test.id}`}
        className="flex flex-row m-5"
        onClick={handleClick}
      >
        <div
          className={`box w-48 rounded-l-lg flex-shrink-0 drop-shadow-md`}
          style={{ backgroundColor: bgColor }}
        ></div>
        <div className="flex flex-col bg-[#FEFAE0] hover:bg-[#FEFAE0] border-t border-r border-b border-[#606C38] rounded-r-lg shadow md:max-w-xl p-4">
          <h2 className="mb-2 text-2xl font-bold tracking-tight text-[#DDA15E]">
            {test.title}
          </h2>
          <p className="mb-3 font-normal text-[#606C38]">{test.description}</p>
          <p className="mb-3 font-normal text-[#606C38]">
            Version: {test.version}
          </p>
        </div>
      </Link>
    </>
  );
}
