import TestsClient from '../../components/TestsClient';
import getTests from '../util/tests';

export const dynamic = 'force-dynamic';

export default async function Tests() {
  const tests = await getTests();

  return <TestsClient tests={tests._embedded.hollandCodeTests} />;
}
