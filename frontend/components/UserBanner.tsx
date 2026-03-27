'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { useTranslation } from '../hooks/useTranslation';

export default function UserBanner() {
  const { t } = useTranslation();
  const router = useRouter();
  const [name, setName] = useState<string | null>(null);

  useEffect(() => {
    const firstName = localStorage.getItem('firstName');
    const lastName = localStorage.getItem('lastName');
    const email = localStorage.getItem('email');

    if (!email) {
      router.replace('/select-user');
      return;
    }
    setName(`${firstName ?? ''} ${lastName ?? ''}`.trim());
  }, [router]);

  const handleSwitchUser = () => {
    localStorage.removeItem('email');
    localStorage.removeItem('firstName');
    localStorage.removeItem('lastName');
    localStorage.removeItem('gender');
    router.push('/select-user');
  };

  if (!name) return null;

  return (
    <div className="flex flex-wrap items-center justify-between gap-3 bg-[#FEFAE0] rounded-xl px-5 py-3 mb-6 shadow">
      <span className="text-[#606C38] font-semibold">
        {t('hi_user', { name })}
      </span>
      <div className="flex items-center gap-3">
        <Link
          href="/history"
          className="text-sm text-[#BC6C25] hover:text-[#a05820] font-medium underline underline-offset-2 transition-colors"
        >
          {t('view_history')}
        </Link>
        <button
          onClick={handleSwitchUser}
          className="text-sm bg-[#ffffff2e] hover:bg-[#ffffff50] border border-[#606C38] text-[#606C38] font-medium px-3 py-1 rounded-lg transition-colors"
        >
          {t('switch_user')}
        </button>
      </div>
    </div>
  );
}
