'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { LocaleProvider } from '../../context/LocaleContext';
import LanguageSwitcher from '../../components/LanguageSwitcher';
import { useTranslation } from '../../hooks/useTranslation';
import { getTestHistory } from '../util/history';

function HistoryContent() {
  const { t } = useTranslation();
  const router = useRouter();
  const [attempts, setAttempts] = useState<ITestAttemptSummary[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const email =
      typeof window !== 'undefined' ? localStorage.getItem('email') : null;
    if (!email) {
      router.replace('/select-user');
      return;
    }

    getTestHistory(email).then((data) => {
      setAttempts(data);
      setLoading(false);
    });
  }, [router]);

  const formatDate = (dateStr: string) => {
    const d = new Date(dateStr);
    return d.toLocaleDateString(undefined, {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
    });
  };

  return (
    <div className="min-h-screen px-4 py-8">
      <div className="absolute top-4 right-4">
        <LanguageSwitcher />
      </div>

      <div className="max-w-2xl mx-auto">
        <div className="flex items-center justify-between mb-8">
          <h1 className="text-3xl font-bold text-[#FEFAE0]">
            {t('history_title')}
          </h1>
          <Link
            href="/tests"
            className="text-sm text-[#FEFAE0] hover:text-[#DDA15E] underline underline-offset-2 transition-colors"
          >
            ← {t('back_to_tests')}
          </Link>
        </div>

        {loading && (
          <div className="flex justify-center py-20">
            <div className="w-8 h-8 border-4 border-[#DDA15E] border-t-transparent rounded-full animate-spin" />
          </div>
        )}

        {!loading && attempts.length === 0 && (
          <div className="text-center py-20">
            <p className="text-[#FEFAE0] text-lg mb-4">{t('no_history')}</p>
            <Link
              href="/tests"
              className="inline-block bg-[#BC6C25] hover:bg-[#a05820] text-white font-semibold px-6 py-2 rounded-lg transition-colors"
            >
              {t('take_a_test')}
            </Link>
          </div>
        )}

        {!loading && attempts.length > 0 && (
          <div className="flex flex-col gap-6">
            {[...attempts].reverse().map((attempt) => (
              <div
                key={attempt.id}
                className="bg-[#FEFAE0] rounded-2xl shadow-md p-6"
              >
                <div className="flex flex-wrap items-start justify-between gap-2 mb-3">
                  <div>
                    <h2 className="text-xl font-bold text-[#DDA15E]">
                      {attempt.testTitle}
                    </h2>
                    <p className="text-sm text-[#767171]">
                      {t('taken_on', { date: formatDate(attempt.date) })}
                    </p>
                  </div>
                  <span className="text-2xl font-bold text-[#BC6C25] bg-[#BC6C2520] px-4 py-1 rounded-xl">
                    {attempt.hollandCode}
                  </span>
                </div>

                {attempt.exactProfessions &&
                  attempt.exactProfessions.length > 0 && (
                    <div className="mb-3">
                      <h3 className="text-sm font-semibold text-[#606C38] mb-1">
                        {t('exact_professions')}
                      </h3>
                      <ul className="flex flex-wrap gap-2">
                        {attempt.exactProfessions.map((p) => (
                          <li
                            key={p.id}
                            className="text-xs bg-[#606C3820] text-[#606C38] border border-[#606C38] rounded-full px-3 py-1"
                          >
                            {p.title}
                          </li>
                        ))}
                      </ul>
                    </div>
                  )}

                {(!attempt.exactProfessions ||
                  attempt.exactProfessions.length === 0) && (
                  <p className="text-xs text-[#767171] mb-3">
                    {t('no_exact_match')}
                  </p>
                )}

                {attempt.similarProfessions &&
                  attempt.similarProfessions.length > 0 && (
                    <div>
                      <h3 className="text-sm font-semibold text-[#606C38] mb-1">
                        {t('similar_professions')}
                      </h3>
                      <ul className="flex flex-wrap gap-2">
                        {attempt.similarProfessions.map((p) => (
                          <li
                            key={p.id}
                            className="text-xs bg-[#DDA15E20] text-[#BC6C25] border border-[#DDA15E] rounded-full px-3 py-1"
                          >
                            {p.title}
                          </li>
                        ))}
                      </ul>
                    </div>
                  )}
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default function HistoryPage() {
  return (
    <LocaleProvider>
      <HistoryContent />
    </LocaleProvider>
  );
}
