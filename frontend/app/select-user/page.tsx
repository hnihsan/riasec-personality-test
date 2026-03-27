'use client';

import { useState, useEffect } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';
import { LocaleProvider } from '../../context/LocaleContext';
import LanguageSwitcher from '../../components/LanguageSwitcher';
import { useTranslation } from '../../hooks/useTranslation';
import { lookupUserByEmail } from '../util/user';

type State = 'idle' | 'loading' | 'found' | 'not_found';

function SelectUserContent() {
  const { t } = useTranslation();
  const router = useRouter();
  const searchParams = useSearchParams();
  const returnTo = searchParams.get('returnTo') || '/tests';

  const [email, setEmail] = useState('');
  const [emailError, setEmailError] = useState('');
  const [state, setState] = useState<State>('idle');
  const [foundUser, setFoundUser] = useState<ITestTaker | null>(null);

  // Registration form fields
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [gender, setGender] = useState('');

  useEffect(() => {
    if (typeof window !== 'undefined') {
      const storedEmail = localStorage.getItem('email');
      if (storedEmail) {
        router.replace('/tests');
      }
    }
  }, [router]);

  const validateEmail = (value: string) =>
    /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);

  const handleLookUp = async () => {
    if (!validateEmail(email)) {
      setEmailError('Please enter a valid email address.');
      return;
    }
    setEmailError('');
    setState('loading');
    const user = await lookupUserByEmail(email);
    if (user) {
      setFoundUser(user);
      setState('found');
    } else {
      setState('not_found');
    }
  };

  const handleContinueAsExisting = () => {
    if (!foundUser) return;
    localStorage.setItem('email', foundUser.emailAddress);
    localStorage.setItem('firstName', foundUser.firstName);
    localStorage.setItem('lastName', foundUser.lastName);
    localStorage.setItem('gender', foundUser.gender);
    router.push(returnTo);
  };

  const handleRegister = () => {
    if (!firstName.trim() || !lastName.trim() || !gender) return;
    localStorage.setItem('email', email);
    localStorage.setItem('firstName', firstName);
    localStorage.setItem('lastName', lastName);
    localStorage.setItem('gender', gender);
    router.push(returnTo);
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center px-4">
      <div className="absolute top-4 right-4">
        <LanguageSwitcher />
      </div>

      <div className="bg-[#FEFAE0] rounded-2xl shadow-xl p-8 w-full max-w-md">
        <h1 className="text-2xl font-bold text-[#606C38] mb-6 text-center">
          {t('select_user_title')}
        </h1>

        {/* Step 1: Email Lookup */}
        {(state === 'idle' || state === 'loading') && (
          <div className="flex flex-col gap-4">
            <div>
              <label className="block text-sm font-medium text-[#606C38] mb-1">
                {t('enter_email')}
              </label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                onKeyDown={(e) => e.key === 'Enter' && handleLookUp()}
                className="w-full border border-[#606C38] rounded-lg px-3 py-2 text-[#333] focus:outline-none focus:ring-2 focus:ring-[#DDA15E]"
                placeholder="you@example.com"
                disabled={state === 'loading'}
              />
              {emailError && (
                <p className="text-red-500 text-xs mt-1">{emailError}</p>
              )}
            </div>
            <button
              onClick={handleLookUp}
              disabled={state === 'loading' || !email}
              className="w-full bg-[#BC6C25] hover:bg-[#a05820] text-white font-semibold py-2 rounded-lg transition-colors disabled:opacity-50"
            >
              {state === 'loading' ? '...' : t('look_up')}
            </button>
          </div>
        )}

        {/* Step 2a: Existing user found */}
        {state === 'found' && foundUser && (
          <div className="flex flex-col gap-4">
            <p className="text-center text-[#606C38] font-medium">
              {t('user_found', {
                name: `${foundUser.firstName} ${foundUser.lastName}`,
              })}
            </p>
            <button
              onClick={handleContinueAsExisting}
              className="w-full bg-[#BC6C25] hover:bg-[#a05820] text-white font-semibold py-2 rounded-lg transition-colors"
            >
              {t('continue_as', {
                name: `${foundUser.firstName} ${foundUser.lastName}`,
              })}
            </button>
            <button
              onClick={() => {
                setState('idle');
                setFoundUser(null);
                setEmail('');
              }}
              className="w-full bg-[#ffffff2e] hover:bg-[#ffffff50] border border-[#606C38] text-[#606C38] font-semibold py-2 rounded-lg transition-colors"
            >
              ← {t('enter_email')}
            </button>
          </div>
        )}

        {/* Step 2b: New user registration */}
        {state === 'not_found' && (
          <div className="flex flex-col gap-4">
            <p className="text-sm text-[#606C38] text-center">
              {t('user_not_found')}
            </p>
            <p className="text-xs text-[#767171] text-center">{email}</p>
            <div>
              <label className="block text-sm font-medium text-[#606C38] mb-1">
                {t('first_name')}
              </label>
              <input
                type="text"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                className="w-full border border-[#606C38] rounded-lg px-3 py-2 text-[#333] focus:outline-none focus:ring-2 focus:ring-[#DDA15E]"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-[#606C38] mb-1">
                {t('last_name')}
              </label>
              <input
                type="text"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                className="w-full border border-[#606C38] rounded-lg px-3 py-2 text-[#333] focus:outline-none focus:ring-2 focus:ring-[#DDA15E]"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-[#606C38] mb-1">
                {t('gender')}
              </label>
              <select
                value={gender}
                onChange={(e) => setGender(e.target.value)}
                className="w-full border border-[#606C38] rounded-lg px-3 py-2 text-[#333] focus:outline-none focus:ring-2 focus:ring-[#DDA15E]"
              >
                <option value="">{t('select_gender')}</option>
                <option value="Male">{t('male')}</option>
                <option value="Female">{t('female')}</option>
                <option value="Non-Binary">{t('non_binary')}</option>
                <option value="Other">{t('other')}</option>
              </select>
            </div>
            <button
              onClick={handleRegister}
              disabled={!firstName.trim() || !lastName.trim() || !gender}
              className="w-full bg-[#BC6C25] hover:bg-[#a05820] text-white font-semibold py-2 rounded-lg transition-colors disabled:opacity-50"
            >
              {t('register')}
            </button>
            <button
              onClick={() => {
                setState('idle');
                setEmail('');
              }}
              className="w-full bg-[#ffffff2e] hover:bg-[#ffffff50] border border-[#606C38] text-[#606C38] font-semibold py-2 rounded-lg transition-colors"
            >
              ← {t('enter_email')}
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default function SelectUserPage() {
  return (
    <LocaleProvider>
      <SelectUserContent />
    </LocaleProvider>
  );
}
