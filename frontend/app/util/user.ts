export async function lookupUserByEmail(
  email: string
): Promise<ITestTaker | null> {
  try {
    const res = await fetch(
      `${
        process.env.NEXT_PUBLIC_API_URL
      }/testTakers/search/findByEmailAddress?emailAddress=${encodeURIComponent(
        email
      )}`
    );
    if (!res.ok) return null;
    const data = await res.json();
    return data as ITestTaker;
  } catch {
    return null;
  }
}
