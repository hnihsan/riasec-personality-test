import { useLocale } from '../context/LocaleContext';
import en from '../messages/en.json';
import id from '../messages/id.json';

const messages: Record<string, Record<string, string>> = { en, id };

export function useTranslation() {
  const { locale } = useLocale();

  function t(key: string, params?: Record<string, string | number>): string {
    const dict = messages[locale] ?? messages['en'];
    let value = dict[key] ?? messages['en'][key] ?? key;

    if (params) {
      Object.entries(params).forEach(([k, v]) => {
        value = value.replace(`{${k}}`, String(v));
      });
    }

    return value;
  }

  return { t };
}
