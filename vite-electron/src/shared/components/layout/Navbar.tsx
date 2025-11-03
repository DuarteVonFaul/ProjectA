// src/components/layout/Navbar.tsx
import { NavLink } from 'react-router-dom';

// A navbar não precisa mais receber props para funcionar
const Navbar = () => {
  // Lista de abas para facilitar a renderização
  const navItems = [
    { to: "/conciliacoes", label: "Conciliações" },
    { to: "/integrador", label: "Integrador" },
    { to: "/configuracao", label: "Configuração" },
  ];

  // Função para definir as classes CSS com base no estado de 'isActive'
  const getNavLinkClass = ({ isActive }: { isActive: boolean }): string => {
    const baseClasses = "py-4 px-6 text-sm font-medium transition-colors duration-300";
    const activeClasses = "text-white bg-gray-900"; // Estilo para a aba ativa
    const inactiveClasses = "text-gray-400 hover:text-white hover:bg-gray-700"; // Estilo para abas inativas

    return `${baseClasses} ${isActive ? activeClasses : inactiveClasses}`;
  };

  return (
    <nav className="bg-gray-800 shadow-lg">
      <div className="container mx-auto">
        <div className="flex items-center justify-start h-16">
          {navItems.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              // O NavLink permite uma função no className para aplicar estilos condicionalmente
              className={getNavLinkClass}
            >
              {item.label}
            </NavLink>
          ))}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;