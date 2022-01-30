export function removeCookie(cname: string): void {
  const expires = 'expires=Thu, 01 Jan 1970 00:00:00 UTC;';
  document.cookie = cname + '=;' + expires + ';path=/';
}

export function setCookie(cname: string, cvalue: string, hours = 1): void {
  const d = new Date();
  d.setTime(d.getTime() + hours * 60 * 60 * 1000); // (exdays * 24 * 60 * 60 * 1000));
  const expires = 'expires=' + d.toUTCString();
  document.cookie = cname + '=' + cvalue + ';' + expires + ';path=/';
}

export function getCookie(cname: string): string | null {
  const name = cname + '=';
  const ca = document.cookie.split(';');

  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) === 0) {
      return c.substring(name.length, c.length);
    }
  }

  return '';
}

export function checkCookie(): string | null {
  const user = getCookie('token');
  if (user !== '') {
    return user;
  } else {
    return null;
  }
}
