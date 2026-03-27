'use client';
import { useEffect } from 'react';
import { useRouter } from 'next/navigation';

export default function TestStart({
  params: { id },
}: {
  params: { id: number };
}) {
  const router = useRouter();

  useEffect(() => {
    const email =
      typeof window !== 'undefined' ? localStorage.getItem('email') : null;
    if (email) {
      router.replace(`/tests/${id}/quiz`);
    } else {
      router.replace(`/select-user?returnTo=/tests/${id}/quiz`);
    }
  }, [id, router]);

  return (
    <div className="min-h-screen flex items-center justify-center">
      <div className="w-8 h-8 border-4 border-[#DDA15E] border-t-transparent rounded-full animate-spin" />
    </div>
  );
}
