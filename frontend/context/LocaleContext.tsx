'use client';

import { createContext, useState, useContext } from 'react';

export type Locale = 'en' | 'id';

interface LocaleContextValue {
  locale: Locale;
  setLocale: (locale: Locale) => void;
}

export const LocaleContext = createContext<LocaleContextValue>({
  locale: 'en',
  setLocale: () => {},
});

export function LocaleProvider({ children }: { children: React.ReactNode }) {
  const [locale, setLocale] = useState<Locale>('en');

  return (
    <LocaleContext.Provider value={{ locale, setLocale }}>
      {children}
    </LocaleContext.Provider>
  );
}

export function useLocale() {
  return useContext(LocaleContext);
}
