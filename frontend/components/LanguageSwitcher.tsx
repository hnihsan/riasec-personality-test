'use client';

import { useLocale } from '../context/LocaleContext';

export default function LanguageSwitcher() {
  const { locale, setLocale } = useLocale();

  return (
    <div className="flex items-center space-x-1">
      <button
        onClick={() => setLocale('en')}
        className={`px-3 py-1 rounded text-sm font-semibold transition-colors ${
          locale === 'en'
            ? 'bg-[#DDA15E] text-[#283618]'
            : 'bg-transparent text-[#FEFAE0]/70 hover:text-[#FEFAE0] hover:bg-[#606C38]/40'
        }`}
      >
        EN
      </button>
      <button
        onClick={() => setLocale('id')}
        className={`px-3 py-1 rounded text-sm font-semibold transition-colors ${
          locale === 'id'
            ? 'bg-[#DDA15E] text-[#283618]'
            : 'bg-transparent text-[#FEFAE0]/70 hover:text-[#FEFAE0] hover:bg-[#606C38]/40'
        }`}
      >
        ID
      </button>
    </div>
  );
}
