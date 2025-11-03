import React from 'react';

interface CardProps {
  title: string;
  children: React.ReactNode;
}

const Card: React.FC<CardProps> = ({ title, children }) => {
  return (
    <div className="bg-white p-6 rounded-lg shadow-md text-center flex-1">
      <h3 className="text-lg font-semibold text-blue-600 mb-2">{title}</h3>
      <p className="text-2xl font-bold text-gray-700">{children}</p>
    </div>
  );
};

export default Card;