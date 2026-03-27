'use client';

import { LocaleProvider } from '../context/LocaleContext';
import UserBanner from './UserBanner';
import TestList from './TestList';
import LanguageSwitcher from './LanguageSwitcher';

export default function TestsClient({ tests }: TestListProps) {
  return (
    <LocaleProvider>
      <div className="absolute top-4 right-4">
        <LanguageSwitcher />
      </div>
      <h1 className="text-center font-bold text-[#FEFAE0] text-6xl mt-6 mb-6">
        Available Tests
      </h1>
      <div className="max-w-2xl mx-auto px-4 mb-6">
        <UserBanner />
      </div>
      <TestList tests={tests} />
    </LocaleProvider>
  );
}
