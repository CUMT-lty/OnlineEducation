const validate = (payload, rules) => {
  const fields = Object.keys(payload);
  for (let i = 0; i < fields.length; i++) {
    const field = fields[i];
    const rule = rules[field];
    const value = payload[field];
    const valid = validateField(value, rule);
    if (!valid) {
      alert(`表单错误: ${field}`);
      return false;
    }
  }
  return true;
};