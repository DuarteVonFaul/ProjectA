
interface TextFieldProps {
  label: string;
  value: string;
  onChange: (value: string) => void;
  placeholder?: string;
  disabled?: boolean;
}

export default function TextField({
  label,
  value,
  onChange,
  placeholder = "",
  disabled = false,
}: TextFieldProps) {
  return (
    <div className="flex flex-col gap-1 w-full">
      <label className="font-medium text-gray-700">{label}</label>
      <input
        type="text"
        className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        placeholder={placeholder}
        disabled={disabled}
      />
    </div>
  );
}